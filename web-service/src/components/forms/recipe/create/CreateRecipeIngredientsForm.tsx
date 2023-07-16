import {Input, InputLabel} from "~/components/ui/Input";
import {XCircleIcon} from "@heroicons/react/24/outline";
import React from "react";
import {LineBreak} from "~/components/ui/Line";
import {PrimaryButton} from "~/components/ui/Button";
import {WhiteDiv} from "~/components/ui/StyledDiv";
import {uuid} from "~/utils/StringUtils";


interface IngredientChecklist {
    id: string;
    title: string;
    ingredients: Ingredient[];
}

interface Ingredient {
    id: string;
    name: string;
}

export function CreateRecipeIngredientsForm() {


    const [checklists, setChecklists] = React.useState<IngredientChecklist[]>(
        [
            {
                id: uuid(),
                title: "",
                ingredients: [
                    {
                        id: uuid(),
                        name: ""
                    }
                ]
            }
        ]
    );


    const addChecklist = () => {
        setChecklists([
            ...checklists,
            {
                id: uuid(),
                title: "",
                ingredients: [
                    {
                        id: uuid(),
                        name: ""
                    }
                ]
            }
        ])
    }

    const removeChecklist = (checklistId: string) => {
        const newChecklists = checklists.filter((checklist) => checklist.id !== checklistId);
        setChecklists(newChecklists);
    }

    const addIngredient = (checklistId: string) => {
        const newChecklists = [...checklists];
        const checklist = checklists.find((checklist) => checklist.id === checklistId);
        if (!checklist) {
            return;
        }
        checklist.ingredients.push({
            id: uuid(),
            name: ""
        });
        setChecklists(newChecklists);
    }

    const removeIngredient = (checklistId: string, ingredientId: string) => {
        console.log(checklistId, ingredientId)
        const newChecklists = [...checklists];
        const checklist = checklists.find((checklist) => checklist.id === checklistId);
        if (!checklist) {
            return;
        }

        checklist.ingredients = checklist.ingredients.filter((ingredient) => ingredient.id !== ingredientId);

        setChecklists(newChecklists);
    }

    return (
        <>
            <div className="flex justify-end">
                <PrimaryButton className="normal-case"
                               onClick={addChecklist}>
                    Add New Checklist
                </PrimaryButton>
            </div>

            {
                checklists.map((checklist) => (
                    <div key={checklist.id}>
                        <WhiteDiv className="relative">

                            {
                                checklists.length > 1 &&
                                <button className="absolute top-5 right-5"
                                        onClick={() => removeChecklist(checklist.id)}>
                                    <XCircleIcon className="w-10 h-10 text-gray-300 hover:text-red-500"/>
                                </button>
                            }

                            <Input label="Title"
                                   placeholder="This is a title"
                                   isRequired={true}
                            />


                            <InputLabel>
                                Ingredients
                            </InputLabel>

                            <div className="space-y-2">
                                {
                                    checklist.ingredients.map((ingredient) => (
                                        <div key={ingredient.id}
                                             className="flex items-center space-x-3">
                                            <Input className="w-full"
                                                   placeholder="This is an ingredient"
                                                   isRequired={true}
                                            />

                                            {
                                                checklist.ingredients.length > 1 &&
                                                <button
                                                    onClick={() => removeIngredient(checklist.id, ingredient.id)}>
                                                    <XCircleIcon
                                                        className="w-10 h-10 text-gray-300 hover:text-red-500"/>
                                                </button>
                                            }

                                        </div>
                                    ))
                                }

                                <div className="w-full flex justify-center mt-10">
                                    <button className="btn w-36 normal-case btn-outline btn-primary"
                                            onClick={() => addIngredient(checklist.id)}
                                    >
                                        Add Ingredient
                                    </button>
                                </div>
                            </div>
                        </WhiteDiv>
                        <LineBreak/>
                    </div>
                ))
            }


        </>
    )
}