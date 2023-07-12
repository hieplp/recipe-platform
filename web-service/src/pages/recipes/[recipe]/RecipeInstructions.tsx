import React from "react";
import {clsx} from "clsx";

// --------------------------------------------------------------------------
// XXX RecipeInstruction
// --------------------------------------------------------------------------

type RecipeIntroductionProps = {
    className?: string;
    index: number;
    content: string;
}

const RecipeInstruction = React.forwardRef<HTMLDivElement, RecipeIntroductionProps>(
    (props, ref) => {
        return (
            <div ref={ref}
                 className={clsx(props.className, "flex space-x-2")}>
                <div className="flex justify-center pt-1">
                    <p className="rounded-full
                                  w-5 h-5
                                  text-sm
                                  text-white
                                  text-center
                                  font-bold
                                  bg-blue-700">
                        {props.index}
                    </p>
                </div>

                <div className="">
                    {props.content}
                </div>
            </div>
        )
    })
RecipeInstruction.displayName = "RecipeInstruction";

// --------------------------------------------------------------------------
// XXX RecipeInstructions
// --------------------------------------------------------------------------
type RecipeIntroductionsProps = {
    className?: string;
    instructions: string[];
}

const RecipeInstructions = React.forwardRef<HTMLDivElement, RecipeIntroductionsProps>(
    (props, ref) => {
        return (
            <div ref={ref}
                 className={props.className}>
                {
                    props.instructions.map((introduction, index) => {
                        return (
                            <RecipeInstruction key={index}
                                               index={index + 1}
                                               content={introduction}
                            />
                        )
                    })
                }
            </div>
        )
    })
RecipeInstructions.displayName = "RecipeInstructions";

// --------------------------------------------------------------------------
// XXX Exports
// --------------------------------------------------------------------------
export {RecipeInstructions};