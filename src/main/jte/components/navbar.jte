@param String title = "Home | chilos.dev"

<nav class="bg-gray-900 text-white p-4">
    <div class="container flex mx-auto">
        <a href="/" class="text-xl font-bold align-left ${title.startsWith("Home") ? "text-pink-100" : "hover:text-pink-100"}">chilos.dev</a>
        <div class="flex content-evenly">
            <a href="/poetry" class="mx-2 ${title.equals("Poetry") ? "text-pink-100" : "hover:text-pink-100"}">Poetry</a>
            <a href="/music" class="mx-2 ${title.equals("Music") ? "text-pink-100" : "hover:text-pink-100"}">Music</a>
            <a href="/dashboard" class="mx-2 ${title.equals("Dashboard") ? "text-pink-100" : "hover:text-pink-100"}">Dashboard</a>
            <a href="/logout" class="mx-2 ${title.equals("Logout") ? "text-pink-100" : "hover:text-pink-100"}">Logout</a>
        </div>
    </div>
</nav>
