import {Layout} from "~/components/layouts/Layout";
import React from "react";
import {LineBreak} from "~/components/ui/Line";
import RecipeTitle from "~/pages/recipes/[recipe]/RecipeTitle";
import RecipeSubTitle from "~/pages/recipes/[recipe]/RecipeSubTitle";
import NextImage from "~/components/ui/NextImage";
import RecipeBasicInformation from "~/pages/recipes/[recipe]/RecipeBasicInformation";
import RecipeIngredients from "~/pages/recipes/[recipe]/RecipeIngredients";
import {RecipeNutritionFacts} from "~/pages/recipes/[recipe]/RecipeNutritionFacts";
import {RecipeInstructions} from "~/pages/recipes/[recipe]/RecipeInstructions";

export default function Recipe() {

    const ingredients = [
        {
            label: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.",
            checked: true

        },
        {
            label: "For the dough",
            checked: false
        },
        {
            label: "For the dough",
            checked: false
        },
        {
            label: "For the dough",
            checked: false
        }
    ]

    const nutritionFacts = [
        {
            label: "Calories",
            value: "200",
            unit: "kcal"
        },
        {
            label: "Calories",
            value: "200",
            unit: "kcal"
        },
        {
            label: "Calories",
            value: "200",
            unit: "kcal"
        },
        {
            label: "Calories",
            value: "200",
            unit: "kcal"
        }
    ];

    const instructions = [
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.",
    ];


    return (
        <Layout>
            <div className="relative w-full mt-3 space-y-3">
                <RecipeTitle percent={98}/>
                <LineBreak/>

                <RecipeSubTitle avatar="/avatar.jpg"
                                author="John Doe"
                                date="May 2021"
                                totalComments={88}/>
                <LineBreak/>

                <p className="">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean venenatis a sem hendrerit
                    consectetur. Sed vitae purus tortor. Duis facilisis, eros nec dignissim eleifend, felis tortor
                    tempus eros, sagittis molestie leo mi non leo. Interdum et malesuada fames ac ante ipsum primis
                    in
                    faucibus. Etiam vitae lacus est. Proin elementum dolor sed augue gravida, dapibus finibus diam.
                </p>
                <LineBreak/>

                <div className="flex justify-center">
                    <NextImage alt=""
                               src="/newsletter.jpg"
                               imgClassName="rounded-xl w-full"
                               width={1500}
                               height={300}
                    />
                </div>

                <RecipeBasicInformation className=""/>

                <div className="grid
                                grid-cols-1 md:grid-cols-12
                                gap-0 md:gap-5
                                space-y-5 md:space-y-0
                                ">
                    <div className="col-span-4
                                    space-y-5">
                        <p className="text-2xl font-bold">
                            Ingredients
                        </p>

                        <RecipeIngredients title="For the dough"
                                           ingredients={ingredients}
                                           className="space-y-2"/>

                        <RecipeNutritionFacts facts={nutritionFacts}/>
                    </div>

                    <div className="col-span-8 space-y-3">
                        <p className="text-2xl font-bold">
                            Instructions
                        </p>

                        <RecipeInstructions className="space-y-2"
                                            instructions={instructions}/>
                    </div>

                </div>
            </div>
        </Layout>
    )
}