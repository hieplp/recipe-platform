import React from "react";
import type Recipe from "~/types/Recipe";
import {Card, CardImage, CardTitle} from "~/components/ui/Card";
import {clsx} from "clsx";

// --------------------------------------------------------------------------
// XXX RecommendedRecipe
// --------------------------------------------------------------------------

const RecommendedRecipe = React.forwardRef<
    HTMLAnchorElement,
    React.HTMLAttributes<HTMLAnchorElement> & Recipe
>(({
       className,
       ...props
   }, ref) => (
    <Card ref={ref}
          href={`/recipes/${props.recipeId}`}
          className={clsx(className, "space-y-2 group")}>
        <CardImage src={props.image}
                   className="h-44 xl:h-52 w-full rounded"
        />

        <CardTitle className="group-hover:text-primary dark:text-white">
            {props.title}
        </CardTitle>
    </Card>
));
RecommendedRecipe.displayName = "RecommendedRecipe";

// --------------------------------------------------------------------------
// XXX RecipeRecommendation
// --------------------------------------------------------------------------

const RecipeRecommendation = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement> & { recipes: Recipe[] }
>(({
       className,
       ...props
   }, ref) => (
    <div ref={ref}
         className={clsx(
             "grid",
             "grid-cols-1 md:grid-cols-3 xl:grid-cols-4",
             "gap-5 md:gap-5",
             className
         )}>
        {
            props.recipes.map((recipe) => (
                <RecommendedRecipe key={recipe.recipeId}
                                   {...recipe}/>
            ))
        }
    </div>
));
RecipeRecommendation.displayName = "RecipeRecommendation";

// --------------------------------------------------------------------------
// XXX Exports
// --------------------------------------------------------------------------
export {RecipeRecommendation}