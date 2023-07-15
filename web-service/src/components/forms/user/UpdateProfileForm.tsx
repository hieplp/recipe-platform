import React from "react";
import {PrimaryButton} from "~/components/ui/Button";
import {clsx} from "clsx";
import {Input} from "~/components/ui/Input";

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
        <section className="form-control space-y-2">
            {/*Username*/}
            <Input label="Username"
                   placeholder="hieplp"
                   isDisabled={true}
            />

            {/*Email*/}
            <Input label="Email"
                   placeholder="hiepphuocly@gmail.com"
                   isDisabled={true}
            />

            {/*Full Name*/}
            <Input label="Full Name"
                   placeholder="HiepLP"
                   isRequired={true}
            />

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