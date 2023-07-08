import {PrimaryButton} from "~/components/ui/Button";
import React from "react";
import {useRouter} from "next/router";

export function VerifyForgotPasswordForm() {
    //
    const router = useRouter();

    //
    const resetPassword = () => {
        void router.push("/auth/login");
    }

    return (
        <section className="form-control">
            <div className="w-full mb-5">
                <label className="label">
                    <span className="label-text">Password</span>
                </label>
                <input type="password"
                       placeholder="••••••••"
                       className="input input-md w-full input-bordered"/>
            </div>

            <div className="w-full mb-5">
                <label className="label">
                    <span className="label-text">Confirm Password</span>
                </label>
                <input type="password"
                       placeholder="••••••••"
                       className="input input-md w-full input-bordered"/>
            </div>

            <div className="w-full mb-5">
                <label className="label">
                    <span className="label-text">Verification Code</span>
                </label>
                <input type="number"
                       placeholder="000000"
                       className="input input-md w-full input-bordered"/>
            </div>

            {/**/}
            <PrimaryButton className="w-full normal-case text-lg"
                           isLoading={false}
                           onClick={resetPassword}>
                Reset password
            </PrimaryButton>
        </section>
    )
}