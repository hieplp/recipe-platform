import {HamburgerButton, Menu} from "~/components/ui/Menu";
import {BrandIcon} from "~/components/ui/Icon";
import {Avatar} from "~/components/ui/Avatar";
import React, {useEffect} from "react";
import {clsx} from "clsx";
import {useRouter} from "next/router";

const links = [
    {
        label: 'Homepage',
        href: '/',
        isActive: true
    },
    {
        label: 'Recipes',
        href: '/recipes',
        isActive: false
    },
    {
        label: 'About',
        href: '/about',
        isActive: false
    }
];

const nonAuthLinks = [
    {
        label: 'Sign In',
        href: '/sign-in',
        isActive: false,
        className: 'text-blue-600'
    }
];
const authLinks = [
    {
        label: 'Sign Out',
        href: '/sign-out',
        isActive: false,
        className: 'text-red-600'
    }
];


export function Header() {
    //
    const [scrolled, setScrolled] = React.useState(false);
    const [isLogged, setIsLogged] = React.useState(true);

    //
    const router = useRouter();

    //
    const showMenu = () => {
        const nav = document.getElementById("navbar-sticky");
        if (!nav) {
            return;
        }
        nav.classList.toggle("hidden");
    };


    useEffect(() => {
        const handleScroll = () => {
            setScrolled(window.scrollY > 96);
        }

        window.addEventListener('scroll', handleScroll);

        return () => {
            window.removeEventListener('scroll', handleScroll);
        }
    }, []);

    //
    let className = "fixed w-full z-20 top-0 left-0";
    if (scrolled) {
        className = clsx(className, "border-b-2 shadow-sm bg-white dark:bg-neutral-focus dark:border-neutral-focus");
    } else {
        className = clsx(className, "bg-transparent");
    }

    return (
        <>
            <nav className={className}>
                <div className="max-w-screen-xl
                                flex flex-wrap
                                items-center
                                justify-between
                                border-neutral
                                mx-auto
                                p-4">
                    <BrandIcon/>

                    <div className="flex md:order-2">
                        {
                            isLogged
                                ? <Avatar avatarImage={"https://flowbite.com/docs/images/logo.svg"}
                                          onClick={() => void router.push('/user/profile')}/>
                                : <>
                                    <button className="btn btn-primary
                                                       px-6
                                                       hidden md:block
                                                       text-base normal-case"
                                            onClick={() => {
                                                void router.push('/auth/login');
                                            }}>
                                        Sign In
                                    </button>
                                </>
                        }

                        <HamburgerButton onclick={showMenu}/>
                    </div>

                    <div id="navbar-sticky"
                         className="items-center
                                    justify-between
                                    hidden
                                    w-full
                                    md:flex md:w-auto md:order-1">
                        <Menu links={links}
                              nonAuthLinks={nonAuthLinks}
                              authLinks={authLinks}
                              isLogged={isLogged}/>
                    </div>
                </div>
            </nav>
        </>
    )
}