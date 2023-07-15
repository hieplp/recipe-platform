import React from "react";

// --------------------------------------------------------------------------
// XXX Button
// --------------------------------------------------------------------------

type InputProps = {
    label?: string;
    isRequired?: boolean;
    isDisabled?: boolean;
    type?: "text" | "number" | "email" | "password";
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
            <label className="label">
                <div className="flex label-text space-x-1">
                    <p className="">
                        {props.label}
                    </p>
                    {props.isRequired && <span className="text-red-500">(*)</span>}
                </div>
            </label>
            <input type={props.type || "text"}
                   className="input input-md w-full input-bordered"
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
                    <p className="">
                        {props.label}
                    </p>
                    {props.isRequired && <span className="text-red-500">(*)</span>}
                </div>
            </label>
            <textarea placeholder={props.placeholder}
                      className="textarea textarea-bordered w-full"
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