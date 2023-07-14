import {SingleImageUpload} from "~/components/ui/ImageUpload";
import React from "react";

export function CreateRecipeBasicInformationForm() {
    return (
        <section className="form-control space-y-3">
            <div className="w-full">
                <label className="label">
                    <span className="label-text">Title</span>
                </label>
                <input type="text"
                       placeholder="This is a title"
                       className="input input-md w-full input-bordered"/>
            </div>

            <div className="w-full">
                <label className="label">
                    <span className="label-text">Description</span>
                </label>
                <textarea placeholder="This is a description"
                          className="textarea textarea-bordered w-full"/>
            </div>

            <label className="label">
                <span className="label-text">Image</span>
            </label>
            <SingleImageUpload/>

            <div className="w-full grid grid-cols-1 md:grid-cols-3 gap-1">
                {/*Preparation Time*/}
                <div className="">
                    <label className="label">
                        <span className="label-text">Preparation Time</span>
                    </label>
                    <input type="number"
                           placeholder="How long does it take to prepare?"
                           className="input input-md w-full input-bordered"/>
                </div>
                {/*Cooking Time*/}
                <div className="">
                    <label className="label">
                        <span className="label-text">Cooking Time</span>
                    </label>
                    <input type="number"
                           placeholder="How long does it take to cook?"
                           className="input input-md w-full input-bordered"/>
                </div>
                {/**/}
                <div className="">
                    <label className="label">
                        <span className="label-text">Servings</span>
                    </label>
                    <input type="number"
                           placeholder="How many servings?"
                           className="input input-md w-full input-bordered"/>
                </div>
            </div>
        </section>
    )
}