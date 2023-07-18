import React from "react";
import {clsx} from "clsx";

type ModalProps = {
    children?: React.ReactNode;
    isOpen?: boolean;
}

// --------------------------------------------------------------------------
// XXX Modal
// --------------------------------------------------------------------------
const Modal = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement> & ModalProps
>(({
       className,
       ...props
   }, ref) => {
    className = clsx(
        className,
        props.isOpen ? "modal-open" : "",
        "modal modal-bottom sm:modal-middle"
    )
    return (
        <div ref={ref}
             className={className}>
            <div className="modal-box">{props.children}</div>
        </div>
    )
});
Modal.displayName = "Modal";

// --------------------------------------------------------------------------
// XXX ConfirmationModal
// --------------------------------------------------------------------------
type ConfirmationModalProps = {
    title: string;
    description: string;
    confirmLabel: string;
    onConfirm: () => void;
    cancelLabel: string;
    onCancel: () => void;
} & ModalProps;

const ConfirmationModal = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement> & ConfirmationModalProps
>(({
       className,
       ...props
   }, ref) => {
    return (
        <Modal ref={ref}
               className={className}
               {...props}>
            <h3 className="font-bold text-lg">
                {props.title}
            </h3>
            <p className="py-4">
                {props.description}
            </p>

            <div className="modal-action">
                <button onClick={props.onCancel}
                        className="btn btn-secondary">
                    {props.cancelLabel || "Cancel"}
                </button>

                <button onClick={props.onConfirm}
                        className="btn btn-primary">
                    {props.confirmLabel || "Confirm"}
                </button>
            </div>
        </Modal>

    )
});
ConfirmationModal.displayName = "ConfirmationModal";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {
    ConfirmationModal
}