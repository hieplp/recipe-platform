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
        //
        let className = props.className;
        if (!className) {
            className = "";
        }

        //

        return (
            <button ref={ref}
                    className={clsx(className, "btn btn-primary")}
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
// XXX Export
// --------------------------------------------------------------------------
export {PrimaryButton}