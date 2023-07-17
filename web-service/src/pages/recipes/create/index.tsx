import {Layout} from "~/components/layouts/Layout";
import {Title} from "~/components/ui/Title";
import React, {useState} from "react";
import {clsx} from "clsx";
import {PrimaryButton, SecondaryButton} from "~/components/ui/Button";
import {CreateRecipeBasicInformationForm} from "~/components/forms/recipe/create/CreateRecipeBasicInformationForm";
import {CreateRecipeIngredientsForm} from "~/components/forms/recipe/create/CreateRecipeIngredientsForm";
import {CreateRecipeNutritionFactsForm} from "~/components/forms/recipe/create/CreateRecipeNutritionFactsForm";
import {CreateRecipeInstructionsForm} from "~/components/forms/recipe/create/CreateRecipeInstructionsForm";
import {CreateRecipeReviewForm} from "~/components/forms/recipe/create/CreateRecipeReviewForm";

interface Step {
    id: number;
    title: string;
    description: string;
}

export default function CreateRecipe() {
    const [currentStep, setCurrentStep] = useState<number>(0);
    const [steps, setSteps] = useState<Step[]>(
        [
            {
                id: 0,
                title: 'Basic Information',
                description: 'Register an account with us'
            },
            {
                id: 1,
                title: 'Ingredients',
                description: 'Choose a plan that works for you'
            },
            {
                id: 2,
                title: 'Nutrition Facts',
                description: 'Purchase the plan'
            },
            {
                id: 3,
                title: 'Instructions',
                description: 'Purchase the plan'
            },
            {
                id: 4,
                title: 'Review',
                description: 'Purchase the plan'
            }
        ]
    );

    //
    const getStep = (index: number): Step => {
        const step = steps[index];
        if (step) {
            return step;
        }
        return {
            id: 0,
            title: '',
            description: ''
        };
    }

    const nextStep = (): void => {
        if (currentStep >= steps.length - 1) {
            return
        }
        goToStep(currentStep + 1);
    }

    const previousStep = (): void => {
        if (currentStep <= 0) {
            return
        }
        goToStep(currentStep - 1);
    }

    const goToStep = (index: number): void => {
        if (index < 0 || index > steps.length - 1) {
            return
        }
        setCurrentStep(index);
    }

    return (
        <Layout>
            <div className="relative w-full space-y-5">
                <Title className="my-3">
                    Create A Recipe
                </Title>

                <div className="w-full flex justify-center">
                    <ul className="steps steps-horizontal">
                        {
                            steps.map(step => (
                                <li key={step.id}
                                    onClick={() => goToStep(step.id)}
                                    className={clsx(
                                        "step ml-4",
                                        "hover:text-primary hover:cursor-pointer",
                                        "dark:text-white",
                                        currentStep >= step.id && "step-primary"
                                    )}>
                                    {step.title}
                                </li>
                            ))
                        }

                    </ul>
                </div>

                {/*Basic Information*/}
                {
                    currentStep === 0 &&
                    <CreateRecipeBasicInformationForm/>
                }

                {/*Ingredients*/}
                {
                    currentStep === 1 &&
                    <CreateRecipeIngredientsForm/>
                }

                {/*Nutrition Facts*/}
                {
                    currentStep === 2 &&
                    <CreateRecipeNutritionFactsForm/>
                }

                {/*Instructions*/}
                {
                    currentStep === 3 &&
                    <CreateRecipeInstructionsForm/>
                }

                {/*Review*/}
                {
                    currentStep === 4 &&
                    <CreateRecipeReviewForm/>
                }

                <div className="w-full flex">
                    {
                        currentStep > 0 &&
                        <SecondaryButton className="normal-case" onClick={previousStep}>
                            Back ({getStep(currentStep - 1).title})
                        </SecondaryButton>
                    }

                    {
                        currentStep < steps.length - 1 &&
                        <SecondaryButton className="normal-case ml-auto" onClick={nextStep}>
                            Next ({getStep(currentStep + 1).title})
                        </SecondaryButton>
                    }

                    {
                        currentStep === steps.length - 1 &&
                        <PrimaryButton className="normal-case ml-auto">
                            Finish
                        </PrimaryButton>
                    }
                </div>
            </div>
        </Layout>
    )
}