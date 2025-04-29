/*
 * @file index.js
 * @description This file contains the main JavaScript logic for the game map and village interaction.
 */

// ============================================================================
//  Initialization
// ============================================================================

/*
 * Load global resources from local storage or initialize if they don't exist.
 * These resources represent the player's accumulated resources.
 */
var globalResources = JSON.parse(localStorage.getItem('globalResources')) || { food: 0, wood: 0, rocks: 0, iron: 0, coal: 0, villagers: 0, knights: 0 };

/*
 * Wait for the DOM to load before executing the main script.
 * This ensures that all HTML elements are available before attaching event listeners.
 */
window.addEventListener('load', () => {

    // Get references to various elements
    const map = document.getElementById('map');
    const button = document.getElementById("village-action-btn");
    const villageImgs = document.querySelectorAll(".village-img");
    const tooltip = document.getElementById("village-tooltip");
    const tooltipText = document.getElementById("tooltip-text");
    const wrapper = document.getElementById('map-wrapper');
    const spyglass = document.getElementById('spyglass-overlay');

    // Initialize zoom variables
    let zoomedIn = false;
    const zoomFactor = 4.8;
    let zoomOrigin = { x: 0, y: 0 };
    let hideButtonTimeout; // Timeout to hide the button

    const initialScroll = {
        left: wrapper.scrollLeft,
        top: wrapper.scrollTop
    };

    // ============================================================================
    //  Button Hover Effects
    // ============================================================================

    /*
     * Add event listener for mouseenter event on the village action button.
     * This changes the button's background and text color when the mouse cursor enters the button area.
     */
    button.addEventListener("mouseenter", () => {
        button.style.backgroundColor = "rgba(0, 102, 110, 0.517)";
        button.style.color = "white";
    });

    /*
     * Add event listener for mouseleave event on the village action button.
     * This reverts the button's background and text color and hides the button when the mouse cursor leaves the button area.
     */
    button.addEventListener("mouseleave", () => {
        button.style.backgroundColor = "rgba(66, 66, 66, 0.517)";
        button.style.color = "white";
        button.style.display = "none";
    });

    // ============================================================================
    //  Village Image Hover Effects
    // ============================================================================

    /*
     * Iterate over each village image and attach mouseenter and mouseleave event listeners.
     * These listeners control the display of the village tooltip and action button.
     */
    villageImgs.forEach(img => {
        img.addEventListener("mouseenter", () => {
            clearTimeout(hideButtonTimeout);
            const rect = img.getBoundingClientRect();
            const mapRect = map.getBoundingClientRect();

            const left = rect.left - wrapper.getBoundingClientRect().left + img.offsetWidth / 2;
            const top = rect.top - wrapper.getBoundingClientRect().top - 10;
            const btnLeft = left - 25;
            const btnTop = top + img.offsetHeight + 10;

            const src = img.getAttribute("src");
            const fileName = src.split("/").pop().replace(".png", "");
            const formatted = fileName.replace(/[-_]/g, " ").replace(/\b\w/g, c => c.toUpperCase());

            const population = img.dataset.population;
            const structures = img.dataset.structures;
            const x = img.dataset.x;
            const y = img.dataset.y;
            const villageId = img.dataset.villageId;

            button.dataset.villageId = villageId;
            button.dataset.population = population;
            button.dataset.structures = structures;
            button.dataset.name = formatted;


            // Attach village ID to the button
            button.setAttribute("data-village-id", villageId); // Set the village ID as a data attribute on the button.

            tooltipText.innerHTML = `
                <strong>${formatted}</strong><br>
                <em>ID: ${villageId}</em><br>
                Population: ${population}<br>
                Structures: ${structures}<br>
                Coordinates: (${x}, ${y})
            `;

            // State change: Display the tooltip and the button
            tooltip.style.left = `${left}px`; // Set the left position of the tooltip.
            tooltip.style.top = `${top}px`; // Set the top position of the tooltip.
            tooltip.style.display = "block"; // Show the tooltip.

            button.style.left = `${btnLeft}px`; // Set the left position of the button.
            button.style.top = `${btnTop}px`; // Set the top position of the button.
            button.style.display = "block"; // Show the button.
        });

        img.addEventListener("mouseleave", () => {
            // This event listener is triggered when the mouse leaves a village image area.
            // State change: Hide the tooltip
            tooltip.style.display = "none"; // Hide the tooltip.
            hideButtonTimeout = setTimeout(() => {
                if (!button.matches(":hover")) {
                    // State change: Hide the button after a delay if not hovered
                    button.style.display = "none"; // Hide the button.
                }
            }, 3000); // 3 seconds delay
        });

    });

    // ============================================================================
    //  Enemy Image Hover Effects
    // ============================================================================

    /*
     * Iterate over each enemy image and attach mouseenter and mouseleave event listeners.
     * These listeners control the display of the enemy tooltip.
     */
    const enemyImgs = document.querySelectorAll(".enemy-img");
    const enemyTooltip = document.getElementById("enemy-tooltip");
    const enemyTooltipText = document.getElementById("enemy-tooltip-text");

    enemyImgs.forEach(enemy => {
        /*
         * Add event listener for mouseenter event on each enemy image.
         * This displays the enemy tooltip with the enemy's level.
         */
        enemy.addEventListener("mouseenter", () => {
            const level = enemy.dataset.level;
            const rect = enemy.getBoundingClientRect();
            const mapRect = map.getBoundingClientRect();

            const left = rect.left - mapRect.left + enemy.offsetWidth / 2;
            const top = rect.top - mapRect.top - 10;

            enemyTooltipText.textContent = `Lvl ${level}`;
            enemyTooltip.style.left = `${left}px`;
            enemyTooltip.style.top = `${top}px`;
            enemyTooltip.style.display = "block";
        });

/*
 * @file index.js
 * @description This file contains the main JavaScript logic for the game map and village interaction.
 */
        enemy.addEventListener("mouseleave", () => {
            enemyTooltip.style.display = "none";
        });
    });


/*
 * Wait for the DOM to load before executing the main script.
 * This ensures that all HTML elements are available before attaching event listeners.
 */
    button.addEventListener("mouseleave", () => {
        /**
         * Set a timeout to hide the button after 3 seconds.
         */
        hideButtonTimeout = setTimeout(() => {
            /**
             * Hide the button.
             */
            button.style.display = "none";
        }, 3000);
    });

    // ============================================================================
    //  Button Click Event
    // ============================================================================

    /**
     * Add event listener for click event on the village action button.
     * This redirects the user to the village page.
     */
    button.addEventListener("click", () => {
        /**
         * Get the village ID from the button's data attribute.
         */
        const villageId = button.dataset.villageId;
        /**
         * Log the village ID to the console.
         */
        console.log("Redirecting to village ID:", villageId);
        /**
         * Redirect the user to the village page.
         */
        window.location.href = "/village/" + villageId;
    });


    /**
     * Function to update spyglass position based on the given coordinates.
     * @param {number} x - The x coordinate.
     * @param {number} y - The y coordinate.
     */
    function updateSpyglassPosition(x, y) {
        /**
         * Get the bounding rectangle of the map wrapper.
         */
        const wrapperRect = wrapper.getBoundingClientRect();
        /**
         * Get the bounding rectangle of the map.
         */
        const mapRect = map.getBoundingClientRect();
        /**
         * Calculate the X coordinate of the spyglass position relative to the screen.
         */
        const screenX = x - wrapper.scrollLeft + mapRect.left;
        /**
         * Calculate the Y coordinate of the spyglass position relative to the screen.
         */
        const screenY = y - wrapper.scrollTop + mapRect.top;

        /**
         * Set the left position of the spyglass.
         */
        spyglass.style.left = `${screenX - 60}px`;
        /**
         * Set the top position of the spyglass.
         */
        spyglass.style.top = `${screenY - 10}px`;
        /**
         * Set the width of the spyglass.
         */
        spyglass.style.width = '65px';
        /**
         * Set the height of the spyglass.
         */
        spyglass.style.height = 'auto';
        /**
         * Set the scale of the spyglass.
         */
        spyglass.style.transform = `scale(${zoomFactor})`;
    }

    // ============================================================================
    //  Double Click Event
    // ============================================================================

    /**
     * Add event listener for double click event on the map.
     * This zooms in and out of the map.
     */
    map.addEventListener('dblclick', (e) => {
        /**
         * Get the bounding rectangle of the map.
         */
        const rect = map.getBoundingClientRect();
        /**
         * Calculate the X coordinate of the double-click relative to the map.
         */
        const x = e.clientX - rect.left + wrapper.scrollLeft;
        /**
         * Calculate the Y coordinate of the double-click relative to the map.
         */
        const y = e.clientY - rect.top + wrapper.scrollTop;

        /**
         * Toggle the zoomedIn state.
         */
        zoomedIn = !zoomedIn;

        /**
         * Add event listener for mousemove event on the document.
         */
        document.addEventListener('mousemove', (e) => {
            /**
             * If the map is zoomed in.
             */
            if (zoomedIn) {
                /**
                 * Define an X offset for the spyglass position.
                 */
                const offsetX = 60;
                /**
                 * Define a Y offset for the spyglass position.
                 */
                const offsetY = 60;
                /**
                 * Update the left position of the spyglass based on the mouse's X coordinate.
                 */
                spyglass.style.left = `${e.clientX - offsetX}px`;
                /**
                 * Update the top position of the spyglass based on the mouse's Y coordinate.
                 */
                spyglass.style.top = `${e.clientY - offsetY}px`;
            }
        });

        /**
         * If the map is zoomed in.
         */
        if (zoomedIn) {
            /**
             * Set the zoom origin to the coordinates of the double-click.
             */
            zoomOrigin = { x, y };
            /**
             * Set the transform origin of the map to the double-click coordinates.
             */
            map.style.transformOrigin = `${x}px ${y}px`;
            /**
             * Scale the map by the zoom factor.
             */
            map.style.transform = `scale(${zoomFactor})`;
            /**
             * Show the spyglass.
             */
            spyglass.style.opacity = '1';
            /**
             * Update the position of the spyglass.
             */
            updateSpyglassPosition(x, y);
        } else {
            /**
             * Reset the transform origin of the map to the original zoom origin.
             */
            map.style.transformOrigin = `${zoomOrigin.x}px ${zoomOrigin.y}px`;
            /**
             * Reset the scale of the map to 1 (original size).
             */
            map.style.transform = `scale(1)`;

            /**
             * After a short delay.
             */
            setTimeout(() => {
                /**
                 * Scroll to the initial position.
                 */
                wrapper.scrollTo({
                    left: initialScroll.left,
                    top: initialScroll.top,
                    behavior: 'smooth'
                });
                /**
                 * Hide the spyglass.
                 */
                spyglass.style.opacity = '0';
            }, 900);
        }
    });

    // ============================================================================
    //  Scroll Event
    // ============================================================================

    /**
     * Add scroll event listener to update spyglass position.
     */
    wrapper.addEventListener('scroll', () => {
        /**
         * If the map is zoomed in.
         */
        if (zoomedIn) {
            /**
             * Get the bounding rectangle of the map.
             */
            const rect = map.getBoundingClientRect();
            /**
             * Calculate the X coordinate of the zoom origin relative to the map.
             */
            const x = zoomOrigin.x + wrapper.scrollLeft - rect.left;
            /**
             * Calculate the Y coordinate of the zoom origin relative to the map.
             */
            const y = zoomOrigin.y + wrapper.scrollTop - rect.top;
            /**
             * Update the position of the spyglass.
             */
            updateSpyglassPosition(x, y);
        }
    });

    // ============================================================================
    //  Prevent Default Events
    // ============================================================================

    /**
     * Prevent default gesture and wheel events.
     */
    document.addEventListener('gesturestart', (e) => e.preventDefault());
    /**
     * Add wheel event listener to prevent default behavior when the control key is pressed.
     */
    document.addEventListener('wheel', (e) => {
        /**
         * If the control key is pressed.
         */
        if (e.ctrlKey) e.preventDefault();
    }, { passive: false });

    // ============================================================================
    //  Dijkstra Button Click
    // ============================================================================

    /**
     * Get the Dijkstra card element.
     */
    const dijkstraCard = document.getElementById('dijkstra-card');
    /**
     * If the Dijkstra card element exists.
     */
    if (dijkstraCard) {
        /**
         * Add click event listener to the Dijkstra card.
         */
        dijkstraCard.addEventListener('click', () => {
            try {
                /**
                 * Play the Dijkstra animation.
                 */
                playDijkstraAnimation();
            } catch (e) {
                /**
                 * Log any errors to the console.
                 */
                console.error("Error playing Dijkstra animation:", e);
            }
        });
    }
});
