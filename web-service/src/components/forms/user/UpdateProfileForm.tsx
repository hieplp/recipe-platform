import React from "react";
import {PrimaryButton} from "~/components/ui/Button";
import {clsx} from "clsx";

// --------------------------------------------------------------------------
// XXX LoginForm - Newsletter
// --------------------------------------------------------------------------
type NewsletterProps = {
    className?: string;
    checked: boolean;
}

const Newsletter = React.forwardRef<HTMLDivElement, NewsletterProps>(
    (props, ref) => {
        return (
            <div ref={ref}
                 className={clsx(props.className, "")}>
                <p className="text-xl font-bold">
                    Newsletter
                </p>
                <div className="form-control">
                    <label className="label cursor-pointer">
                        <p className="label-text">
                            Receive updates about new products and offers
                        </p>
                        <input type="checkbox" className="toggle"/>
                    </label>
                </div>
            </div>
        )
    })
Newsletter.displayName = 'Newsletter';

// --------------------------------------------------------------------------
// XXX LoginForm
// --------------------------------------------------------------------------
export function UpdateProfileForm() {
    return (
        <section className="form-control">
            {/*Username*/}
            <div className="w-full mb-5">
                <label className="label">
                    <span className="label-text">Username</span>
                </label>
                <input type="text"
                       disabled={true}
                       placeholder="hieplp"
                       className="input input-md w-full input-bordered"/>
            </div>

            {/*Email*/}
            <div className="w-full mb-5">
                <label className="label">
                    <span className="label-text">Email</span>
                </label>
                <input type="text"
                       disabled={true}
                       placeholder="hiepphuocly@gmail.com"
                       className="input input-md w-full input-bordered"/>
            </div>

            {/*Full Name*/}
            <div className="w-full mb-5">
                <label className="label">
                    <span className="label-text">Full Name</span>
                </label>
                <input type="text"
                       placeholder="HiepLP"
                       className="input input-md w-full input-bordered"/>
            </div>

            {/*Newsletter*/}
            <Newsletter className="mb-5" checked={true}/>

            {/**/}
            <PrimaryButton className="w-full normal-case text-lg"
                           isLoading={false}>
                Save
            </PrimaryButton>
        </section>
    )
}