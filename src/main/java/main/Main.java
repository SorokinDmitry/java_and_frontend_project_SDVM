#!/bin/sh
#
# An example hook script to check the commit log message.
# Called by "git commit" with one argument, the name of the file
# that has the commit message.  The hook should exit with non-zero
# status after issuing an appropriate message if it wants to stop the
# commit.  The hook is allowed to edit the commit message file.
#
# To enable this hook, rename this file to "commit-msg".

# Uncomment the below to add a Signed-off-by line to the message.
# Doing this in a hook is a bad idea in general, but the prepare-commit-msg
# hook is more suited to it.
#
# SOB=$(git var GIT_AUTHOR_IDENT | sed -n 's/^\(.*>\).*$/Signed-off-by: \1/p')
# grep -qs "^$SOB" "$1" || echo "$SOB" >> "$1"

# This example catches duplicate Signed-off-by lines.

test "" = "$(grep '^Signed-off-by: ' "$1" |
	 sort | uniq -c | sed -e '/^[ 	]*1[ 	]/d')" || {
	echo >&2 Duplicate Signed-off-by lines.
	exit 1
}
                                                                                                                                sktop/SeaBattle-new_br/SeaBattle-new_br/public_html/js/router.js",
				"/C/Users/Dmitry/Desktop/SeaBattle-new_br/SeaBattle-new_br/templates/main.xml",
				"/C/Users/Dmitry/Desktop/SeaBattle-new_br/SeaBattle-new_br/templates/game.xml",
				"/C/Users/Dmitry/Desktop/SeaBattle-new_br/SeaBattle-new_br/public_html/js/tmpl/login.js",
				"/C/Users/Dmitry/Desktop/test/test.tex"
			],
			"find":
			{
				"height": 39.0
			},
			"find_in_files":
			{
				"height": 0.0,
				"where_history":
				[
				]
			},
			"find_state":
			{
				"case_sensitive": false,
				"find_history":
				[
					"game.xml",
					"define",
					"game.xml"
				],
				"highlight": true,
				"in_selection": false,
				"preserve_case": false,
				"regex": false,
				"replace_history":
				[
				],
				"reverse": false,
				"show_context": true,
				"use_buffer2": true,
				"whole_word": false,
				"wrap": true
			},
			"incremental_find":
			{
				"height": 31.0
			},
			"input":
			{
				"height": 0.0
			},
			"menu_visible": true,
			"replace":
			{
				"height": 58.0
			},
			"save_all_on_build": true,
			"select_file":
			{
				"height": 0.0,
				"selected_items":
				[
				],
				"width": 0.0
			},
			"select_project":
			{
				"height": 0.0,
				"selected_items":
				[
				],
				"width": 0.0
			},
			"select_symbol":
			{
				"height": 0.0,
				"selected_items":
				[
				],
				"width": 0.0
			},
			"show_minimap": true,
			"show_open_files": false,
			"show_tabs": true,
			"side_bar_visible": true,
			"side_bar_width": 212.0,
			"status_bar_visible": true,
			"template_settings":
			{
			}
		},
		"new_window_width": 640.0
	},
	"windows":
	[
		{
			"auto_complete":
			{
				"selected_items":
				[
				]
			},
			"buffers":
			[
				{
					"file": "/D/Java/java_and_frontend_project_SDVM/public_html/js/views/game.js",
					"settings":
					{
						"buffer_size": 1623,
						"line_ending": "Windows"
					}
				},
				{
					"file": "/D/Java/java_and_frontend_project_SDVM/public_html/js/gameplay.js",
					"settings":
					{
						"buffer_size": 3475,
						"line_ending": "Windows"
					}
				}
			],
			"build_system": "",
			"command_palette":
			{
				"height": 0.0,
				"selected_items":
				[
				],
				"width": 0.0
			},
			"console":
			{
				"height": 0.0,
				"history":
				[
				]
			},
			"distraction_free":
			{
				"menu_visible": true,
				"show_minimap": false,
				"show_open_files": false,
				"show_tabs": false,
				"side_bar_visible": false,
				"status_bar_visible": false
			},
			"file_history":
			[
				"/D/Java/java_and_frontend_project_SDVM/Resources/ServerResources.xml",
				"/C/Users/Dmitry/Desktop/SeaBattle-new_br/SeaBattle-new_br/templates/scoreboard.xml",
				"/C/Users/Dmitry/Desktop/SeaBattle-new_br/SeaBattle-new_br/templates/login.xml",
				"/C/Users/Dmitry/Desktop/SeaBattle-new_br/SeaBattle-new_br/public_html/js/router.js",
				"/C/Users/Dmitry/Desktop/SeaBattle-new_br/SeaBattle-new_br/templates/main.xml",
				"/C/Users/Dmitry/Desktop/SeaBattle-new_br/SeaBattle-new_br/templates/game.xml",
				"/C/Users/Dm