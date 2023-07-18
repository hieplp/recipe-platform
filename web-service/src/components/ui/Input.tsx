import React from "react";
import {clsx} from "clsx";

type CommonProps = {
    label?: string;
    isRequired?: boolean;
    isDisabled?: boolean;
    labelClassName?: string;
    itemClassName?: string;
}

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
        <label ref={ref}
               className={clsx(
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
    type?: "text" | "number" | "email" | "password";
};
const Input = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLInputElement> & CommonProps & InputProps
>(({
       className,
       ...props
   }, ref) => {
    return (
        <div ref={ref}
             className={className}>
            {
                props.label &&
                <InputLabel isRequired={props.isRequired} className={props.labelClassName}>
                    {props.label}
                </InputLabel>
            }

            <input type={props.type || "text"}
                   className={clsx(
                       props.itemClassName,
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
    React.HTMLAttributes<HTMLTextAreaElement> & CommonProps & TextareaProps
>(({
       className,
       ...props
   }, ref) => {
    return (
        <div ref={ref}
             className={className}>
            {
                props.label &&
                <InputLabel isRequired={props.isRequired} className={props.labelClassName}>
                    {props.label}
                </InputLabel>
            }
            <textarea placeholder={props.placeholder}
                      className={clsx(
                          props.itemClassName,
                          "textarea textarea-bordered w-full"
                      )}
            />
        </div>
    )
});
Textarea.displayName = "Textarea";

// --------------------------------------------------------------------------
// XXX Select
// --------------------------------------------------------------------------

interface SelectOption {
    value: string | number;
    label: string;
}

type SelectProps = {
    label?: string;
    options: SelectOption[];
}

const Select = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLSelectElement> & CommonProps & SelectProps
>(({
       className,
       ...props
   }, ref) => {
    return (
        <div ref={ref}
             className={className}>
            {
                props.label &&
                <InputLabel isRequired={props.isRequired} className={props.labelClassName}>
                    {props.label}
                </InputLabel>
            }

            <select className={clsx("select select-bordered w-full", props.itemClassName)}>
                {
                    props.options.map((option: SelectOption) => {
                        return (
                            <option key={option.value}
                                    value={option.value}>
                                {option.label}
                            </option>
                        )
                    })
                }
            </select>
        </div>
    )
});
Select.displayName = "Select";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------

export {
    Input,
    Textarea,
    Select,
    InputLabel
};