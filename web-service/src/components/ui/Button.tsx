import React from "react";
import {clsx} from "clsx";

// --------------------------------------------------------------------------
// XXX Button
// --------------------------------------------------------------------------

type ButtonProps = {
    label: string;
    onclick?: () => void;
    className?: string;
    textSize?: string;
    textWeight?: string;
};

const Button = React.forwardRef<HTMLButtonElement, ButtonProps>(
    (props, ref) => {

        let className = props.className;
        if (!className) {
            className = "";
        }
        className = clsx(className,
            "text-center",
            "px-4 py-2",
            "border border-transparent",
            "rounded-md shadow-sm",
            "text-white",
            "focus:outline-none ",
            "focus:ring-2 focus:ring-offset-2");

        //
        if (props.textSize) {
            className = clsx(className, "text-" + props.textSize);
        } else {
            className = clsx(className, "text-md");
        }

        //
        if (props.textWeight) {
            className = clsx(className, "font-" + props.textWeight);
        } else {
            className = clsx(className, " font-medium");
        }

        return (
            <button ref={ref}
                    type="button"
                    onClick={props.onclick}
                    className={className}>
                {props.label}
            </button>
        )
    });
Button.displayName = "Button";

// --------------------------------------------------------------------------
// XXX Button - Primary
// --------------------------------------------------------------------------
const PrimaryButton = React.forwardRef<HTMLButtonElement, ButtonProps>(
    (props, ref) => {
        let className = props.className;
        if (!className) {
            className = "";
        }
        className = clsx(className,
            "text-white",
            "bg-blue-700 hover:bg-blue-800",
            "focus:ring-4 focus:outline-none focus:ring-blue-300");

        return (
            <Button ref={ref}
                    textSize={props.textSize}
                    textWeight={props.textWeight}
                    label={props.label}
                    onclick={props.onclick}
                    className={className}/>
        )
    });
PrimaryButton.displayName = "PrimaryButton";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {Button, PrimaryButton}