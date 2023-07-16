import React from "react";
import {clsx} from "clsx";

// --------------------------------------------------------------------------
// XXX Styled Div - White
// --------------------------------------------------------------------------

type WhiteDivProps = {
    className?: string,
    children?: React.ReactNode,
    xPadding?: number,
    yPadding?: number,
    xSpace?: number,
    ySpace?: number,
}
const WhiteDiv = React.forwardRef<HTMLDivElement, WhiteDivProps>(
    (props, ref) => {
        return (
            <>
                <div ref={ref}
                     className={clsx(
                         props.className,
                         "bg-white rounded-lg shadow",
                         `px-${(props.xPadding ? props.xPadding : 8)}`,
                         `py-${(props.yPadding ? props.yPadding : 8)}`,
                         `space-x-${(props.xSpace ? props.xSpace : 0)}`,
                         `space-y-${(props.ySpace ? props.ySpace : 3)}`,
                     )}>
                    {props.children}
                </div>
            </>
        )
    });
WhiteDiv.displayName = "WhiteDiv";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {WhiteDiv};