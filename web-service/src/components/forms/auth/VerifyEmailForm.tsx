import React from "react";
import {useRouter} from "next/router";
import {PrimaryButton} from "~/components/ui/Button";

export function VerifyEmailForm() {
    const router = useRouter();

    const verify = () => {
        void router.push("/auth/login");
    }

    return (
        <section className="form-control">
            {/*Username*/}
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
                           onClick={verify}
                           isLoading={false}>
                Verify
            </PrimaryButton>
        </section>
    )
}