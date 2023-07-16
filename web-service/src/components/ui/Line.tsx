import React from "react";
import {clsx} from "clsx";

// --------------------------------------------------------------------------
// XXX LineBreak
// --------------------------------------------------------------------------

type LineBreakProps = {
    className?: string;
}

const LineBreak = React.forwardRef<HTMLDivElement, LineBreakProps>(
    (props, ref) => {
        let className = clsx(props.className,
            "flex",
            "items-center justify-between",
            "w-full",
            "text-gray-700",
            "border-b",
            "border-gray-100",
            "dark:border-gray-800",
        );

        return (
            <div ref={ref}
                 className={className}>
            </div>
        )
    });
LineBreak.displayName = "LineBreak";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------

export {LineBreak}
