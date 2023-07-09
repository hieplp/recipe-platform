import React from "react";
import Link from "next/link";

// --------------------------------------------------------------------------
// XXX LineBreak
// --------------------------------------------------------------------------

type StyledLinkProps = {
    className?: string,
    children?: React.ReactNode,
    href: string,
};

const StyledLink = React.forwardRef<HTMLDivElement, StyledLinkProps>(
    (props, ref) => {
        return (
            <div ref={ref}
                 className={props.className}>
                <Link href={props.href}>
                    {props.children}
                </Link>
            </div>
        )
    });
StyledLink.displayName = "StyledLink";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {StyledLink}
