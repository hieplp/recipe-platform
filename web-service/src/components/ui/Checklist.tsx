import React from "react";
import {clsx} from "clsx";

// --------------------------------------------------------------------------
// XXX Checklist - Item
// --------------------------------------------------------------------------

type ChecklistItemProps = {
    className?: string;
    label: string;
    checked: boolean;
}

const ChecklistItem = React.forwardRef<HTMLLabelElement, ChecklistItemProps>(
    (props, ref) => {
        return (
            <label ref={ref}
                   className={clsx(props.className, "cursor-pointer flex items-center space-x-2")}>
                <input type="checkbox"
                       className="checkbox checkbox-sm checkbox-primary"
                       checked={props.checked}
                />
                <p className="">
                    {props.label}
                </p>
            </label>
        )
    });
ChecklistItem.displayName = "ChecklistItem";

// --------------------------------------------------------------------------
// XXX Checklist
// --------------------------------------------------------------------------
type ChecklistProps = {
    className?: string;
    itemClassName?: string;
    list: ChecklistItemProps[];
}

const Checklist = React.forwardRef<HTMLDivElement, ChecklistProps>(
    (props, ref) => {
        return (
            <div ref={ref}
                 className={clsx(props.className, "space-y-2")}>
                {
                    props.list.map((item, index) => {
                        return (
                            <ChecklistItem key={index}
                                           label={item.label}
                                           checked={item.checked}
                                           className={props.itemClassName}
                            />
                        )
                    })
                }
            </div>
        )
    })
Checklist.displayName = "Checklist";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {Checklist, ChecklistItem, type ChecklistItemProps}