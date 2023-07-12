import React from "react";
import {clsx} from "clsx";

// --------------------------------------------------------------------------
// XXX Button
// --------------------------------------------------------------------------

type ButtonProps = {
    children?: React.ReactNode;
    onClick?: () => void;
    className?: string;
    textSize?: string;
    textWeight?: string;
    isLoading?: boolean | false;
};

// --------------------------------------------------------------------------
// XXX Button - Primary
// --------------------------------------------------------------------------
const PrimaryButton = React.forwardRef<HTMLButtonElement, ButtonProps>(
    (props, ref) => {
        return (
            <button ref={ref}
                    className={clsx(props.className, "btn btn-primary")}
                    onClick={props.onClick}
                    disabled={props.isLoading}
            >
                {props.isLoading &&
                    <div hidden={!props.isLoading}
                         className="loading loading-sm loading-spinner"/>
                }
                {props.children}
            </button>
        )
    });
PrimaryButton.displayName = "PrimaryButton";

// --------------------------------------------------------------------------
// XXX Button - Secondary
// --------------------------------------------------------------------------
const SecondaryButton = React.forwardRef<HTMLButtonElement, ButtonProps>(
    (props, ref) => {
        return (
            <button ref={ref}
                    className={clsx(props.className, "btn btn-secondary text-blue-500")}
                    onClick={props.onClick}
                    disabled={props.isLoading}
            >
                {props.isLoading &&
                    <div hidden={!props.isLoading}
                         className="loading loading-sm loading-spinner"/>
                }
                {props.children}
            </button>
        )
    });
SecondaryButton.displayName = "SecondaryButton";

// --------------------------------------------------------------------------
// XXX Button - Icon
// --------------------------------------------------------------------------
const OnlyIconButton = React.forwardRef<HTMLDivElement, ButtonProps>(
    (props, ref) => {
        return (
            <div ref={ref}
                 className={clsx(props.className, "hover:cursor-pointer hover:text-blue-700")}>
                {props.children}
            </div>
        )
    });
OnlyIconButton.displayName = "OnlyIconButton";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {
    PrimaryButton,
    SecondaryButton,
    OnlyIconButton
}