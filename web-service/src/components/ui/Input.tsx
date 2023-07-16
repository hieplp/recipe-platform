import React from "react";
import {clsx} from "clsx";

// --------------------------------------------------------------------------
// XXX Button
// --------------------------------------------------------------------------

type InputProps = {
    label?: string;
    isRequired?: boolean;
    isDisabled?: boolean;
    type?: "text" | "number" | "email" | "password";
    inputClassName?: string;
};
const Input = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLInputElement> & InputProps
>(({
       className,
       ...props
   }, ref) => {
    return (
        <div ref={ref}
             className={className}>
            {
                props.label &&
                <label className="label">
                    <div className="flex label-text space-x-1">
                        <p className="">
                            {props.label}
                        </p>
                        {props.isRequired && <span className="text-red-500">(*)</span>}
                    </div>
                </label>
            }

            <input type={props.type || "text"}
                   className={clsx(
                       props.inputClassName,
                       "input input-md w-full input-bordered")
                   }
                   disabled={props.isDisabled}
                   {...props}
            />
        </div>
    )
});
Input.displayName = "Input";

// --------------------------------------------------------------------------
// XXX Textarea
// --------------------------------------------------------------------------
type TextareaProps = {
    cols?: number;
}

const Textarea = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLTextAreaElement> & InputProps & TextareaProps
>(({
       className,
       ...props
   }, ref) => {
    return (
        <div ref={ref}
             className={className}>
            <label className="label">
                <div className="flex label-text space-x-1">
                    <p className="dark:text-white">
                        {props.label}
                    </p>
                    {props.isRequired && <span className="text-red-500">(*)</span>}
                </div>
            </label>
            <textarea placeholder={props.placeholder}
                      className={clsx(
                          props.inputClassName,
                          "textarea textarea-bordered w-full"
                      )}
                      {...props}
            />
        </div>
    )
});
Textarea.displayName = "Textarea";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------

export {Input, Textarea};