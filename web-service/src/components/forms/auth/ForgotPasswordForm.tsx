import {PrimaryButton} from "~/components/ui/Button";
import React from "react";
import {useRouter} from "next/router";

export function ForgotPasswordForm() {
    //
    const router = useRouter();

    //
    const forgotPassword = () => {
        void router.push("/auth/forgot-password/verify");
    }

    return (
        <section className="form-control">
            <div className="w-full mb-5">
                <label className="label">
                    <span className="label-text">Email</span>
                </label>
                <input type="email"
                       placeholder="hiepphuocly@gmail.com"
                       className="input input-md w-full input-bordered"/>
            </div>

            {/**/}
            <PrimaryButton className="w-full normal-case text-lg"
                           isLoading={false}
                           onClick={forgotPassword}>
                Continue
            </PrimaryButton>
        </section>
    )
}