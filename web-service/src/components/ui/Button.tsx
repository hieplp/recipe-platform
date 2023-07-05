import React from "react";

// --------------------------------------------------------------------------
// XXX Button
// --------------------------------------------------------------------------

type ButtonProps = {
    label: string;
    onclick?: () => void;
    className?: string;
};

const Button = React.forwardRef<HTMLButtonElement, ButtonProps>(
    ({
         label,
         onclick,
         className
     }, ref) => {
        if (!className) {
            className = "";
        }
        className = className
            + " inline-flex items-center"
            + " px-4 py-2"
            + " border border-transparent"
            + " rounded-md shadow-sm"
            + " text-sm font-medium text-white"
            + " focus:outline-none "
            + " focus:ring-2 focus:ring-offset-2"
        ;
        return (
            <button ref={ref}
                    type="button"
                    onClick={onclick}
                    className={className}>
                {label}
            </button>
        )
    });
Button.displayName = "Button";

// --------------------------------------------------------------------------
// XXX Button - Primary
// --------------------------------------------------------------------------
const PrimaryButton = React.forwardRef<HTMLButtonElement, ButtonProps>(
    ({
         label,
         onclick,
         className
     }, ref) => {

        if (!className) {
            className = "";
        }
        className = className
            + " text-white text-md"
            + " bg-blue-700 hover:bg-blue-800"
            + " focus:ring-4 focus:outline-none focus:ring-blue-300"
        ;
        return (
            <Button ref={ref}
                    label={label}
                    onclick={onclick}
                    className={className}/>
        )
    });
PrimaryButton.displayName = "PrimaryButton";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {Button, PrimaryButton}