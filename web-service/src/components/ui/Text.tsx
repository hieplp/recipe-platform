import React from "react";
import {clsx} from "clsx";
import {InputLabel} from "~/components/ui/Input";

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
// XXX BorderedText
// --------------------------------------------------------------------------
type BorderedTextProps = {
    label?: string,
    children: React.ReactNode,
    labelClassName?: string,
}

const BorderedText = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement> & BorderedTextProps
>(({
       className,
       ...props
   }, ref) => (
    <div ref={ref} className={className}>
        <InputLabel className={props.labelClassName}>
            {props.label}
        </InputLabel>
        <div className="flex items-center h-auto py-2 px-4
                        input input-md w-full input-bordered">
            {props.children}
        </div>
    </div>
));
BorderedText.displayName = "BorderedText";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {AlignVerticalText, BorderedText};