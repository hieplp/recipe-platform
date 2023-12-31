import {clsx} from "clsx";
import React from "react";

function Skeleton({
                      className,
                      ...props
                  }: React.HTMLAttributes<HTMLDivElement>) {
    return (
        <div
            className={clsx("animate-pulse rounded-md bg-primary/10", className)}
            {...props}
        />
    )
}

export {Skeleton}