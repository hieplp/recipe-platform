import {Layout} from "~/components/layouts/Layout";
import React from "react";
import {LineBreak} from "~/components/ui/Line";
import RecipeTitle from "~/pages/recipes/[recipe]/RecipeTitle";
import RecipeSubTitle from "~/pages/recipes/[recipe]/RecipeSubTitle";


// --------------------------------------------------------------------------
// XXX Recipe
// --------------------------------------------------------------------------
export default function Recipe() {
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

            </div>
        </Layout>
    )
}