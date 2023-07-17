// --------------------------------------------------------------------------
// XXX RecipeCardList
// --------------------------------------------------------------------------
import React from "react";
import {clsx} from "clsx";
import {RecipeCard} from "~/components/recipe/RecipeCard";
import type Recipe from "~/types/Recipe";

const RecipeCardList = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement> & { recipes: Recipe[] }
>(({
       className,
       ...props
   }, ref) => {
    return (
        <>
            <div ref={ref}
                 className={clsx(className, "grid mb-12")}>
                {
                    props.recipes.map(recipe => (
                        <RecipeCard key={recipe.recipeId}
                                    {...recipe}
                        />
                    ))
                }
            </div>
        </>
    )
});
RecipeCardList.displayName = "RecipeCardList";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {RecipeCardList}