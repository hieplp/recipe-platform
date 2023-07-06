import React from "react";
import {clsx} from "clsx";

// --------------------------------------------------------------------------
// XXX AlignVerticalText
// --------------------------------------------------------------------------
type AlignVerticalTextProps = {
    className?: string,
    text: string,
}
const AlignVerticalText = React.forwardRef<HTMLDivElement, AlignVerticalTextProps>(
    ({
         className,
         text
     }, ref) => {
        return (
            <div ref={ref}
                 className={clsx(className, "flex items-center")}>
                <p className="m-auto">
                    {text}
                </p>
            </div>
        )
    });
AlignVerticalText.displayName = "AlignVerticalText";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {AlignVerticalText};