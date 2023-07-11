import React from "react";
import {clsx} from "clsx";

// --------------------------------------------------------------------------
// XXX RecipeBasicInformationTab
// --------------------------------------------------------------------------
type RecipeBasicInfoTabProps = {
    className?: string,
    title: string,
    value: number,
    oneUnitText: string,
    manyUnitsText: string,
}

const RecipeBasicInfoTab = React.forwardRef<HTMLDivElement, RecipeBasicInfoTabProps>(
    (props, ref) => {
        return (
            <div ref={ref}
                 className={props.className}>
                <p className="text-neutral-content ">
                    {props.title}
                </p>
                <div className="flex space-x-1">
                    <p className="font-bold">
                        {props.value}
                    </p>
                    <p>
                        {props.value <= 1 ? props.oneUnitText : props.manyUnitsText}
                    </p>
                </div>
            </div>
        )
    })
RecipeBasicInfoTab.displayName = "Tab";

// --------------------------------------------------------------------------
// XXX RecipeBasicInformation
// --------------------------------------------------------------------------
type RecipeBasicInformationProps = {
    className?: string,
}

const RecipeBasicInformation = React.forwardRef<HTMLDivElement, RecipeBasicInformationProps>(
    (props, ref) => {
        return (
            <div ref={ref}
                 className={clsx(props.className, "flex space-x-5")}>

                <RecipeBasicInfoTab title="PREP TIME"
                                    value={2}
                                    oneUnitText="minute"
                                    manyUnitsText="minutes"/>
                <div className="divider divider-horizontal"/>
                <RecipeBasicInfoTab title="COOKING TIME"
                                    value={15}
                                    oneUnitText="minute"
                                    manyUnitsText="minutes"/>

                <div className="divider divider-horizontal"></div>
                <RecipeBasicInfoTab title="SERVINGS"
                                    value={3}
                                    oneUnitText="person"
                                    manyUnitsText="people"/>
            </div>
        )
    });
RecipeBasicInformation.displayName = "RecipeBasicInformation";

export default RecipeBasicInformation;