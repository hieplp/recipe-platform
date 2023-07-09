import React from "react";
import {PrimaryButton} from "~/components/ui/Button";


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


            {/**/}
            <PrimaryButton className="w-full normal-case text-lg"
                           isLoading={false}>
                Save
            </PrimaryButton>
        </section>
    )
}