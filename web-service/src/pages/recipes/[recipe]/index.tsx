import {Layout} from "~/components/layouts/Layout";
import React from "react";
import {LineBreak} from "~/components/ui/Line";
import RecipeTitle from "~/pages/recipes/[recipe]/RecipeTitle";
import RecipeSubTitle from "~/pages/recipes/[recipe]/RecipeSubTitle";
import NextImage from "~/components/ui/NextImage";
import RecipeBasicInformation from "~/pages/recipes/[recipe]/RecipeBasicInformation";
import RecipeIngredients from "~/pages/recipes/[recipe]/RecipeIngredients";
import {RecipeNutritionFacts} from "~/pages/recipes/[recipe]/RecipeNutritionFacts";

export default function Recipe() {

    const ingredients = [
        {
            label: "For the dough",
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
    ]

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

                <RecipeBasicInformation/>


                <div className="grid grid-cols-1 md:grid-cols-12 gap-0 md:gap-5">

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

                    <div className="col-span-8 bg-amber-200">
                        2
                    </div>

                </div>
            </div>
        </Layout>
    )
}