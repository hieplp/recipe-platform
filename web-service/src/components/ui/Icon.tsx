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
                  passHref={true}
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
                                 hover:text-primary">
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