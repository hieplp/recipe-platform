import {PrimaryButton} from "~/components/ui/Button";
import React from "react";
import {useRouter} from "next/router";
import {Input} from "~/components/ui/Input";

export function ForgotPasswordForm() {
    //
    const router = useRouter();

    //
    const forgotPassword = () => {
        void router.push("/auth/forgot-password/verify");
    }

    return (
        <section className="form-control space-y-5">
            <Input label="Email"
                   placeholder="hiepphuocly@gmail.com"
                   isRequired={true}
                   type="email"
            />

            {/**/}
            <PrimaryButton className="w-full normal-case text-lg"
                           isLoading={false}
                           onClick={forgotPassword}>
                Continue
            </PrimaryButton>
        </section>
    )
}