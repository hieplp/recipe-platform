import React from "react";
import {clsx} from "clsx";

// --------------------------------------------------------------------------
// XXX RecipeNutrition
// --------------------------------------------------------------------------
type RecipeNutritionProps = {
    className?: string,
    label: string,
    value: string
    unit: string
}
const RecipeNutrition = React.forwardRef<HTMLDivElement, RecipeNutritionProps>(
    (props, ref) => {
        return (
            <div ref={ref}
                 className={clsx(props.className, "flex")}>
                <p className="">
                    {props.label}
                </p>
                <p className="ml-auto">
                    {props.value} {props.unit}
                </p>
            </div>
        )
    });
RecipeNutrition.displayName = "RecipeNutrition";

// --------------------------------------------------------------------------
// XXX RecipeNutritionFacts
// --------------------------------------------------------------------------200
type RecipeNutritionFactsProps = {
    facts: RecipeNutritionProps[]
}

const RecipeNutritionFacts = React.forwardRef<HTMLDivElement, RecipeNutritionFactsProps>(
    (props, ref) => {
        return (
            <div ref={ref}
                 className="bg-gray-100
                            dark:bg-gray-800
                            text-black
                            dark:text-white
                            p-2 rounded
                 ">
                <p className="text-2xl font-bold">
                    Nutrition Facts
                </p>
                <div className="mt-5 space-y-2">
                    {
                        props.facts.map((fact, index) => {
                            return (
                                <RecipeNutrition key={index}
                                                 className={index === 0 ? "" : "border-t pt-1"}
                                                 {...fact}
                                />
                            )
                        })
                    }
                </div>
            </div>
        )
    });
RecipeNutritionFacts.displayName = "RecipeNutritionFacts";

export {RecipeNutritionFacts}

