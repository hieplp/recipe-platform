import React from "react";
import {useRouter} from "next/router";
import {PrimaryButton} from "~/components/ui/Button";
import {Input} from "~/components/ui/Input";

export function VerifyEmailForm() {
    const router = useRouter();

    const verify = () => {
        void router.push("/auth/login");
    }

    return (
        <section className="form-control space-y-5">
            {/*Verification Code*/}
            <Input label="Verification Code"
                   placeholder="000000"
                   isRequired={true}
                   type="number"
            />

            {/**/}
            <PrimaryButton className="w-full normal-case text-lg"
                           onClick={verify}
                           isLoading={false}>
                Verify
            </PrimaryButton>
        </section>
    )
}