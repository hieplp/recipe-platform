import React from "react";
import Link from "next/link";
import Image from "next/image";

// --------------------------------------------------------------------------
// XXX BrandIcon
// --------------------------------------------------------------------------

const BrandIcon = React.forwardRef<HTMLAnchorElement>(
    ({}, ref) => {
        return (
            <Link ref={ref}
                  href="/"
                  className="flex items-center">
                <Image src="https://flowbite.com/docs/images/logo.svg"
                       width={32}
                       height={32}
                       className="h-8 mr-3"
                       alt="Flowbite Logo"/>
                <span className="self-center
                                 text-2xl
                                 font-semibold
                                 whitespace-nowrap
                                 text-black dark:text-white
                                 hover:text-blue-600">
                      Recipe.IT
                </span>
            </Link>
        )
    });
BrandIcon.displayName = "BrandIcon";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {BrandIcon};