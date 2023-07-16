import {Layout} from "~/components/layouts/Layout";
import {Title} from "~/components/ui/Title";
import React, {useState} from "react";
import {clsx} from "clsx";
import {WhiteDiv} from "~/components/ui/StyledDiv";
import {PrimaryButton} from "~/components/ui/Button";
import {CreateRecipeBasicInformationForm} from "~/components/forms/recipe/create/CreateRecipeBasicInformationForm";
import {Input} from "~/components/ui/Input";
import {PlusCircleIcon, XCircleIcon} from "@heroicons/react/24/outline";

interface Step {
    id: number;
    title: string;
    description: string;
}

export default function CreateRecipe() {
    const [currentStep, setCurrentStep] = useState<number>(1);
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
            <div className="relative w-full mt-3">
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
                                        currentStep >= step.id && "step-primary"
                                    )}>
                                    {step.title}
                                </li>
                            ))
                        }

                    </ul>
                </div>

                <WhiteDiv className="my-5">
                    {/*Basic Information*/}
                    {
                        currentStep === 0 &&
                        <CreateRecipeBasicInformationForm/>
                    }

                    {/*Ingredients*/}
                    {
                        currentStep === 1 &&
                        <>
                            <div className="">
                                <div className="flex items-center space-x-1">
                                    <Input className="w-full"
                                           placeholder="Search for ingredients"/>

                                    <button className="text-gray-300 hover:text-primary">
                                        <PlusCircleIcon className="w-10 h-10"/>
                                    </button>

                                    <button className="text-gray-300 hover:text-error">
                                        <XCircleIcon className="w-10 h-10"/>
                                    </button>

                                </div>
                            </div>
                        </>
                    }
                </WhiteDiv>

                <div className="w-full flex ">

                    {
                        currentStep > 0 &&
                        <PrimaryButton className="normal-case" onClick={previousStep}>
                            Back ({getStep(currentStep - 1).title})
                        </PrimaryButton>
                    }

                    {
                        currentStep < steps.length - 1 &&
                        <PrimaryButton className="normal-case ml-auto" onClick={nextStep}>
                            Next ({getStep(currentStep + 1).title})
                        </PrimaryButton>
                    }
                </div>
            </div>
        </Layout>
    )
}