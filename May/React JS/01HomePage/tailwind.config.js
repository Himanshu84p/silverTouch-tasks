/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",],
  theme: {
    extend: {
      colors: {
        primaryBg: "#b7d5d4",
        primary: "#373E40",
        dark_slate_grey: "#305252",
        teal: "#488286",
        purple: "#8789c0",
        slate: "#b1a7a6",
        slate_grey: "#2D3335",
        charcoal: "#31383A",
        gunmental_grey: "#3A4042",
      },
  },
  plugins: [],
}
}
