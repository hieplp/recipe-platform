import React from "react";
import {clsx} from "clsx";
import {LineBreak} from "~/components/ui/Line";

// --------------------------------------------------------------------------
// XXX Title
// --------------------------------------------------------------------------
type TitleProps = {
    children?: React.ReactNode;
    className?: string;
    text: string;
}

const Title = React.forwardRef<HTMLDivElement, TitleProps>(
    (props, ref) => {
        return (
            <>
                <div ref={ref}
                     className={clsx(props.className, "")}>
                    <p className="text-3xl font-bold">
                        {props.text}
                    </p>
                    <LineBreak/>
                </div>
            </>
        )
    });
Title.displayName = "Title";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {Title}