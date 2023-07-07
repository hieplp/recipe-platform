import React from "react";

// --------------------------------------------------------------------------
// XXX LineBreak
// --------------------------------------------------------------------------

const LineBreak = React.forwardRef<HTMLDivElement>(
    ({}, ref) => {
        return (
            <div
                ref={ref}
                className="flex
                           items-center justify-between
                           w-full py-2 pl-3 pr-4
                           text-gray-700
                           border-b
                           border-gray-100
                           md:border-0
                           md:p-0
                           md:w-auto
                           dark:text-gray-700">
            </div>
        )
    });
LineBreak.displayName = "LineBreak";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------

export {LineBreak}
