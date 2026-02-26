@import gg.jte.Content
@param String title = "Home | chilos.dev"
@param Content content

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>${title}</title>
    <link rel="stylesheet" href="/css/stylez.css">
</head>
<body class="hx-boost bg-gray-900 text-left w-full h-screen">

    @template.components.navbar(title)

    <main class="container bg-gray-800 w-md mx-auto my-4 p-4 rounded-lg">
        ${content}
    </main>

    <footer class="text-white text-center pb-4">
        &copy; 2026 chilos
    </footer>

    <script src="/js/htmx.min.js"></script>
</body>
</html>
