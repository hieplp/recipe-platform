import React from "react";
import {clsx} from "clsx";

// --------------------------------------------------------------------------
// XXX Styled Div - White
// --------------------------------------------------------------------------

type WhiteDivProps = {
    className?: string,
    children?: React.ReactNode,
    xSpace?: number,
    ySpace?: number,
}
const WhiteDiv = React.forwardRef<HTMLDivElement, WhiteDivProps>(
    (props, ref) => {

        return (
            <section ref={ref}
                     className={clsx(
                         props.className,
                         "bg-white rounded-lg shadow",
                         `space-x-${(props.xSpace ? props.xSpace : 0)}`,
                         `space-y-${(props.ySpace ? props.ySpace : 3)}`,
                         "p-5"
                     )}>
                {props.children}
            </section>
        )
    });
WhiteDiv.displayName = "WhiteDiv";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {WhiteDiv};