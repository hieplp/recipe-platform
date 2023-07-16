import React from "react";
import {clsx} from "clsx";

// --------------------------------------------------------------------------
// XXX Input - Label
// --------------------------------------------------------------------------
type InputLabelProps = {
    children?: React.ReactNode;
    isRequired?: boolean;
}
const InputLabel = React.forwardRef<
    HTMLLabelElement,
    React.HTMLAttributes<HTMLLabelElement> & InputLabelProps
>(({
       className,
       ...props
   }, ref) => {
    return (
        <label className={clsx(
            className,
            "label"
        )}>
            <div className="flex label-text space-x-1">
                <p className="">
                    {props.children}
                </p>
                {props.isRequired && <span className="text-red-500">(*)</span>}
            </div>
        </label>
    )
});
InputLabel.displayName = "InputLabel";

// --------------------------------------------------------------------------
// XXX Input
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
                <InputLabel isRequired={props.isRequired}>
                    {props.label}
                </InputLabel>
            }

            <input type={props.type || "text"}
                   className={clsx(
                       props.inputClassName,
                       "input input-md w-full input-bordered")
                   }
                   disabled={props.isDisabled}
                   placeholder={props.placeholder}
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
            <InputLabel isRequired={props.isRequired}>
                {props.label}
            </InputLabel>
            <textarea placeholder={props.placeholder}
                      className={clsx(
                          props.inputClassName,
                          "textarea textarea-bordered w-full"
                      )}
            />
        </div>
    )
});
Textarea.displayName = "Textarea";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------

export {
    Input,
    Textarea,
    InputLabel
};