import React from "react";
import Link from "next/link";
import {LineBreak} from "~/components/ui/Line";

// --------------------------------------------------------------------------
// XXX Menu
// --------------------------------------------------------------------------

type MenuProps = {
    links: MenuItemProps[],
    nonAuthLinks: MenuItemProps[] | [],
    authLinks: MenuItemProps[] | []
    isLogged?: boolean | false
};

const Menu = React.forwardRef<HTMLUListElement, MenuProps>(
    ({links, nonAuthLinks, authLinks, isLogged}, ref) => {
        return (
            <ul ref={ref}
                className="flex flex-col
                           px-4 py-1 md:p-0 mt-4
                           font-medium
                           border border-gray-100
                           rounded-lg
                           bg-gray-50
                           md:flex-row
                           md:space-x-12
                           md:mt-0
                           md:border-0
                           md:bg-white">
                {/*All common link*/}
                {links.map(({
                                href,
                                label,
                                isActive,
                                className
                            }) => (
                    <li key={`${href}${label}`} className="mt-3">
                        <MenuItem label={label}
                                  href={href}
                                  isActive={isActive}
                                  className={className}/>
                    </li>
                ))}

                <LineBreak/>
                {
                    isLogged
                        ? authLinks.map(({
                                             href,
                                             label,
                                             isActive,
                                             className
                                         }) => (
                            <li key={`${href}${label}`}
                                className="mt-3 block md:hidden w-full">
                                <MenuItem label={label}
                                          href={href}
                                          isActive={isActive}
                                          className={className}/>
                            </li>
                        ))
                        : nonAuthLinks.map(({
                                                href,
                                                label,
                                                isActive,
                                                className
                                            }) => (
                            <li key={`${href}${label}`}
                                className="mt-3 block md:hidden divide-y divide-gray-100">
                                <MenuItem label={label}
                                          href={href}
                                          isActive={isActive}
                                          className={className}/>
                            </li>
                        ))
                }
            </ul>
        )
    });
Menu.displayName = "Menu";

// --------------------------------------------------------------------------
// XXX MenuItem
// --------------------------------------------------------------------------

type MenuItemProps = {
    label: string;
    href: string;
    isActive: boolean | false,
    className?: string
};

const MenuItem = React.forwardRef<HTMLAnchorElement, MenuItemProps>(
    ({label, href, isActive, className}, ref) => {
        if (!className) {
            className = '';
        }
        className = className
            + ' text-lg hover:text-blue-600 font-bold md:bg-transparent'
            + ' block py-2 pl-3 pr-4';
        if (isActive) {
            className = className
                + 'font-bold rounded'
                + ' bg-blue-700 '
                + ' text-white md:text-blue-600';
        }
        return (
            <Link ref={ref}
                  href={href}
                  className={className}
                  aria-current="page">
                {label}
            </Link>
        )
    });
MenuItem.displayName = "MenuItem";

// --------------------------------------------------------------------------
// XXX HamburgerButton
// --------------------------------------------------------------------------

type HamburgerButtonProps = {
    onclick: () => void;
};

const HamburgerButton = React.forwardRef<HTMLButtonElement, HamburgerButtonProps>(
    ({onclick}, ref) => {
        return (
            <button ref={ref}
                    onClick={onclick}
                    type="button"
                    className="inline-flex
                               items-center
                               p-2
                               w-10 h-10
                               justify-center
                               text-sm
                               rounded-lg
                               md:hidden
                               hover:bg-gray-50
                               focus:outline-none
                               focus:ring-2
                               focus:ring-gray-100">
                <span className="sr-only">Open main menu</span>
                <svg className="w-5 h-5"
                     aria-hidden="true"
                     xmlns="http://www.w3.org/2000/svg"
                     fill="none"
                     viewBox="0 0 17 14">
                    <path stroke="currentColor"
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          strokeWidth="2"
                          d="M1 1h15M1 7h15M1 13h15"/>
                </svg>
            </button>
        )
    });
HamburgerButton.displayName = "HamburgerButton";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------

export {Menu, MenuItem, HamburgerButton}