import {PrimaryButton} from "~/components/ui/Button";
import React from "react";
import {useRouter} from "next/router";
import {Input} from "~/components/ui/Input";

export function VerifyForgotPasswordForm() {
    //
    const router = useRouter();

    //
    const resetPassword = () => {
        void router.push("/auth/login");
    }

    return (
        <section className="form-control space-y-3">
            {/*Password*/}
            <Input label="Password"
                   placeholder="••••••••"
                   isRequired={true}
                   type="password"
            />

            {/*Confirm Password*/}
            <Input label="Confirm Password"
                   placeholder="••••••••"
                   isRequired={true}
                   type="password"
            />

            {/*Verification Code*/}
            <Input label="Verification Code"
                   placeholder="000000"
                   isRequired={true}
                   type="number"
            />

            {/**/}
            <PrimaryButton className="w-full normal-case text-lg"
                           isLoading={false}
                           onClick={resetPassword}>
                Reset password
            </PrimaryButton>
        </section>
    )
}