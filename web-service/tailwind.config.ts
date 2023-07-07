import {type Config} from "tailwindcss";

export default {
    content: ["./src/**/*.{js,ts,jsx,tsx}"],
    theme: {
        extend: {},
    },
    daisyui: {
        themes: [
            {
                mytheme: {
                    "primary": "#1d4ed8",
                    "secondary": "#d926a9",
                    "accent": "#1fb2a6",
                    "neutral": "#2a323c",
                    "base-100": "#1d232a",
                    "info": "#3abff8",
                    "success": "#36d399",
                    "warning": "#fbbd23",
                    "error": "#f87272",
                },
            },
            "light", "dark",
        ],
    },
    plugins: [require("daisyui")],
} satisfies Config;
