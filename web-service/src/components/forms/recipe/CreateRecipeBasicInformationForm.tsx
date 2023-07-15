import {SingleImageUpload} from "~/components/ui/ImageUpload";
import React from "react";
import {Input, Textarea} from "~/components/ui/Input";

export function CreateRecipeBasicInformationForm() {
    return (
        <section className="form-control space-y-3">

            <Input label="Title"
                   placeholder="This is a title"
                   isRequired={true}
            />

            <Textarea label="Description"
                      placeholder="This is a description"
                      isRequired={true}
            />

            <label className="label">
                <p className="label-text">
                    Image
                    <span className="ml-1 text-red-500">(*)</span>
                </p>
            </label>
            <SingleImageUpload/>

            <div className="w-full grid grid-cols-1 md:grid-cols-3 gap-1">
                {/*Preparation Time*/}
                <Input label="Preparation Time"
                       placeholder="How long does it take to prepare?"
                       isRequired={true}
                       type="number"
                />

                {/*Cooking Time*/}
                <Input label="Cooking Time"
                       placeholder="How long does it take to cook?"
                       isRequired={true}
                       type="number"
                />

                {/*Servings*/}
                <Input label="Servings"
                       placeholder="How many servings?"
                       isRequired={true}
                       type="number"
                />
            </div>
        </section>
    )
}