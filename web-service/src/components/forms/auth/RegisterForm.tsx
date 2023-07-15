import React from "react";
import {useRouter} from "next/router";
import {PrimaryButton} from "~/components/ui/Button";
import {Input} from "~/components/ui/Input";

// --------------------------------------------------------------------------
// XXX RegisterForm
// --------------------------------------------------------------------------
export function RegisterForm() {
    //
    const router = useRouter();

    const register = () => {
        void router.push("/auth/register/verify");
    }

    return (
        <section className="form-control space-y-3">
            {/*Username*/}
            <Input label="Username"
                   placeholder="hieplp"
                   isRequired={true}
            />

            {/*Email*/}
            <Input label="Email"
                   placeholder="hiepphuocly@gmail.com"
                   isRequired={true}
                   type="email"
            />

            {/*FullName*/}
            <Input label="Full Name"
                   placeholder="HiepLP"
                   isRequired={true}
            />

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

            {/**/}
            <PrimaryButton className="btn btn-primary w-full normal-case text-lg"
                           onClick={register}>
                Register
            </PrimaryButton>
        </section>
    )
}