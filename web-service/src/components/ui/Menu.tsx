import React from "react";
import Link from "next/link";
import {LineBreak} from "~/components/ui/Line";
import {clsx} from "clsx";

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
                           dark:bg-neutral-focus dark:border-neutral-focus
                           md:flex-row
                           md:space-x-12
                           md:mt-0
                           md:border-0
                           md:bg-transparent">
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
        className = clsx(className,
            'text-lg hover:text-blue-600 font-bold md:bg-transparent',
            'block py-2 pl-3 pr-4');
        if (isActive) {
            className = clsx(className,
                'font-bold rounded',
                'bg-blue-700 ',
                'text-white md:text-blue-600');
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
        const [isOn, setIsOn] = React.useState(false);
        return (
            <button ref={ref}
                    onClick={() => {
                        setIsOn(!isOn);
                        onclick();
                    }}
                    type="button"
                    className="btn swap swap-rotate
                               p-2
                               md:hidden
                               focus:outline-none
                               bg-transparent
                               ring-0
                               border-0
                               hover:bg-gray-100
                               ">

                {/* hamburger icon */}
                {isOn
                    ?
                    <>
                        <svg className="swap-on fill-current"
                             xmlns="http://www.w3.org/2000/svg"
                             width="32"
                             height="32"
                             viewBox="0 0 512 512">
                            <polygon
                                points="400 145.49 366.51 112 256 222.51 145.49 112 112 145.49 222.51 256 112 366.51 145.49 400 256 289.49 366.51 400 400 366.51 289.49 256 400 145.49"/>
                        </svg>
                        <svg className="fill-current"
                             xmlns="http://www.w3.org/2000/svg"
                             width="32"
                             height="32"
                             viewBox="0 0 512 512">
                            <polygon
                                points="400 145.49 366.51 112 256 222.51 145.49 112 112 145.49 222.51 256 112 366.51 145.49 400 256 289.49 366.51 400 400 366.51 289.49 256 400 145.49"/>
                        </svg>
                    </>

                    :
                    <svg className="swap-off fill-current"
                         xmlns="http://www.w3.org/2000/svg"
                         width="32"
                         height="32"
                         viewBox="0 0 512 512">
                        <path d="M64,384H448V341.33H64Zm0-106.67H448V234.67H64ZM64,128v42.67H448V128Z"/>
                    </svg>
                }
            </button>
        )
    });
HamburgerButton.displayName = "HamburgerButton";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------

export {Menu, MenuItem, HamburgerButton}