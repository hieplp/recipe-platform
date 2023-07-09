import React from "react";
import {clsx} from "clsx";

// --------------------------------------------------------------------------
// XXX Styled Div - White
// --------------------------------------------------------------------------

type WhiteDivProps = {
    className?: string,
    children?: React.ReactNode
}
const WhiteDiv = React.forwardRef<HTMLDivElement, WhiteDivProps>(
    (props, ref) => {
        return (
            <>
                <div ref={ref}
                     className={clsx(props.className, "bg-white rounded-lg shadow")}>
                    <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
                        {props.children}
                    </div>
                </div>
            </>
        )
    });
WhiteDiv.displayName = "WhiteDiv";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {WhiteDiv};