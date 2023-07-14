import React from "react";
import Link from "next/link";
import {clsx} from "clsx";
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

const Menu = React.forwardRef<
    HTMLUListElement,
    MenuProps
>(({
       ...props
   }, ref) => {
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
            {props.links.map((link) => (
                <li key={`${link.href}${link.label}`} className="mt-3 group relative">
                    <MenuItem isDropdown={props.isLogged && link.childMenu != null}
                              {...link}/>

                    {/*All child link*/}
                    {
                        props.isLogged &&
                        link.childMenu &&
                        <MenuItemChildren childMenu={link.childMenu}/>
                    }
                </li>
            ))}

            <LineBreak className="md:border-0 md:p-0 md:w-auto"/>

            {/*Auth links*/}
            {
                props.isLogged &&
                props.authLinks.map((link) => (
                    <li key={`${link.href}${link.label}`}
                        className="mt-3 block md:hidden w-full">
                        <MenuItem {...link}/>
                    </li>
                ))
            }

            {/*Non auth links*/}
            {
                !props.isLogged &&
                props.nonAuthLinks.map((link) => (
                    <li key={`${link.href}${link.label}`}
                        className="mt-3 block md:hidden divide-y divide-gray-100">
                        <MenuItem {...link}/>
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
    className?: string,
    childMenu?: MenuItemProps[]
    isDropdown?: boolean | false
};

const MenuItem = React.forwardRef<
    HTMLAnchorElement,
    React.HTMLAttributes<HTMLAnchorElement> & MenuItemProps
>(({
       className,
       ...props
   }, ref) => {
    if (props.isActive) {
        className = clsx(className,
            'font-bold rounded',
            'bg-blue-700 ',
            'text-white md:text-primary');
    }
    return (
        <Link ref={ref}
              href={props.href}
              passHref={true}
              className={clsx(
                  className,
                  "text-lg hover:text-primary font-bold md:bg-transparent",
                  "block py-2 pl-3 pr-4",
                  "flex items-center",
              )}
              aria-current="page">
            {props.label}

            {/*Dropdown icon*/}
            {
                props.isDropdown &&
                <svg className="w-2.5 h-2.5 ml-2.5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
                     fill="none" viewBox="0 0 10 6">
                    <path stroke="currentColor"
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          strokeWidth="2"
                          d="m1 1 4 4 4-4"/>
                </svg>
            }
        </Link>
    )
});
MenuItem.displayName = "MenuItem";

// --------------------------------------------------------------------------
// XXX MenuItem - Child
// --------------------------------------------------------------------------
const MenuItemChild = React.forwardRef<
    HTMLAnchorElement,
    React.HTMLAttributes<HTMLAnchorElement> & MenuItemProps
>(({
       className,
       ...props
   }, ref) => (
    <Link href={props.href}
          ref={ref}
          className={clsx(
              className,
              "block",
              "px-4 py-2",
              "hover:bg-gray-100 dark:hover:bg-gray-600",
              "dark:text-gray-400 dark:hover:text-whit"
          )}
          aria-current="page">
        {props.label}
    </Link>
));
MenuItemChild.displayName = "MenuItemChild";

// --------------------------------------------------------------------------
// XXX MenuItem - LineBreak
// --------------------------------------------------------------------------
const MenuItemChildren = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement> & { childMenu: MenuItemProps[] }
>(({
       className,
       ...props
   }, ref) => (
    <div ref={ref}
         className={clsx(
             className,
             "hidden group-hover:block",
             "relative md:absolute",
             "font-normal",
             "bg-white",
             "divide-y divide-gray-100",
             "rounded-lg shadow",
             "w-full md:w-44",
             "dark:bg-gray-700 dark:divide-gray-600"
         )}>
        <ul className="py-2 text-sm text-gray-700 dark:text-gray-200">
            {props.childMenu.map((props) => (
                <li key={`${props.href}${props.label}`}>
                    <MenuItemChild {...props}/>
                </li>
            ))}
        </ul>
    </div>
));
MenuItemChildren.displayName = "MenuItemChildren";

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

export {
    Menu,
    MenuItem,
    MenuItemChild,
    MenuItemChildren,
    HamburgerButton
}