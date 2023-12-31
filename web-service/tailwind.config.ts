import {type Config} from "tailwindcss";

export default {
    content: [
        "./src/**/*.{js,ts,jsx,tsx}",
    ],
    theme: {
        extend: {},
    },
    daisyui: {
        themes: [
            {
                'light': {
                    "primary": "#1d4ed8",
                    "secondary": "#e9f0ff",
                    "accent": "#1dcdbc",
                    "neutral": "#2b3440",
                    "base-100": "#ffffff",
                    "info": "#3abff8",
                    "success": "#36d399",
                    "warning": "#fbbd23",
                    "error": "#f87272",
                }
            },
        ],
    },
    variants: {
        fill: ["hover", "focus"], // this line does the trick
    },
    plugins: [require("daisyui")],
} satisfies Config;
