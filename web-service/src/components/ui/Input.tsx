import React from "react";
import {clsx} from "clsx";

// --------------------------------------------------------------------------
// XXX Button
// --------------------------------------------------------------------------

type InputProps = {
    className?: string;
    placeholder?: string;
};
const Input = React.forwardRef<HTMLInputElement, InputProps>(
    (props, ref) => {
        if (!props.placeholder) {
            props.placeholder = "";
        }
        return (
            <input ref={ref}
                   type="text"
                   placeholder={props.placeholder}
                   className="input text-black"/>
        )
    });
Input.displayName = "Input";

// --------------------------------------------------------------------------
// XXX PrimaryButton
// --------------------------------------------------------------------------
const PrimaryInput = React.forwardRef<HTMLInputElement, InputProps>(
    (props, ref) => {
        let className = props.className;
        if (!className) {
            className = "";
        }
        className = clsx(className,
            "input",
            "text-black",
            "focus:ring-blue-700",
            "dark:text-white");
        return (
            <input type="text"
                   placeholder={props.placeholder}
                   className={className}/>
        )
    });
PrimaryInput.displayName = "PrimaryInput";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------

export {Input, PrimaryInput};